package com.bhsoftware.projectserver.shiro;

import com.bhsoftware.projectserver.entity.User;
import com.bhsoftware.projectserver.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * shiro用于认证用户~授权
 * @Author:debug (SteadyJack)
 * @Date: 2019/7/30 14:33
 **/
@Component
public class UserRealm extends AuthorizingRealm {

    private static final Logger log= LoggerFactory.getLogger(UserRealm.class);


    @Autowired
    private UserMapper userMapper;



    /**
     * 资源-权限分配 ~ 授权 ~ 需要将分配给当前用户的权限列表塞给shiro的权限字段中去
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection){
        return null;
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
        final String userName=token.getUsername();
        final String password=String.valueOf(token.getPassword());

        User user= userMapper.getUserByUsernameAndPassword(userName,password);
        //User entity=userDao.selectByUserNameAndPassword(userName,password);
        //账户不存在
        if(user==null){
            throw new UnknownAccountException("账户不存在");
        }
        //账户被禁用
        if(0==user.getStatus()){
            throw  new DisabledAccountException("账户已被禁用");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(), ByteSource.Util.bytes(user.getSalt()), user.getRealname());
        log.info("信息是:",info);
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

