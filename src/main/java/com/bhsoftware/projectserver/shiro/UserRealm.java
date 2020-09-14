package com.bhsoftware.projectserver.shiro;

import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.mapper.AdminPermissonMapper;
import com.bhsoftware.projectserver.mapper.AdminRoleMapper;
import com.bhsoftware.projectserver.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import java.util.List;
//import java.util.Set;
/**
 * shiro用于认证用户~授权
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/30 14:33
 **/
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminPermissonMapper adminPermissonMapper;

    /**
     * 资源-权限分配 ~ 授权 ~ 需要将分配给当前用户的权限列表塞给shiro的权限字段中去
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User)principalCollection.getPrimaryPrincipal();
        adminRoleMapper.findRoleByUserName(user.getUsername()).stream().forEach(
            adminRole -> {
                authorizationInfo.addRole(adminRole.getName());
                adminPermissonMapper.findAdminPermissionByRoleId(adminRole.getId()).stream().forEach(
                        adminPerm -> {
                            authorizationInfo.addStringPermission(adminPerm.getDesc());
                        }
                );
            }
        );
        System.out.println("当前对象权限标识"+authorizationInfo.getStringPermissions());
        System.out.println("当前用户角色是"+authorizationInfo.getRoles());
        return authorizationInfo;
    }

    /**
     * 用户认证 ~ 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username=token.getUsername();
        //final String password=String.valueOf(token.getPassword());
        User user= userMapper.selectByUserName(username);
        //账户不存在
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        //账户被禁用
        if(0==user.getStatus()){
            throw  new DisabledAccountException("账户已被禁用");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()),getName());
        Session session= SecurityUtils.getSubject().getSession();
        session.setAttribute("user",user);
        System.out.println("获取session存入的用户"+session.getAttribute("user").toString());
        session.setTimeout(600000);//设置session 10分钟
        System.out.println("当前是"+info);
        return info;
    }

    /**
     * 密码验证器~匹配逻辑 ~ 第二种验证逻辑
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }

}

