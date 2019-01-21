package com.hyh.shiro.config;

import com.hyh.shiro.entity.Permission;
import com.hyh.shiro.entity.User;
import com.hyh.shiro.service.PermissionService;
import com.hyh.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;

    /**
     * 获取当前用户的身份和权限
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        simpleAuthorInfo.addRole(user.getRole().getName());
        List<Permission> permissionList = permissionService.listPermissionByRole(user.getRole());
        for(Permission permission:permissionList){
            simpleAuthorInfo.addStringPermission(permission.getName());
        }
        return simpleAuthorInfo;
    }

    /**
     *  用于验证用户登陆的方法
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        //获取基于用户名和密码的令牌
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String account = token.getUsername();
        User user = userService.getUserByAccount(account);//根据登陆名account从库中查询user对象
        if(user==null){throw new UnknownAccountException();}
        if("0".equals(user.getStatus())){throw new DisabledAccountException();}
        SecurityUtils.getSubject().getSession().setAttribute("user", user);
        //使用用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        //进行认证，将正确数据给shiro处理
        //密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
        /*	第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
         * 第二个参数必须放密码
         * 第三个参数 放盐值 用于
         * 第四个参数放 当前realm的名字，因为可能有多个realm*/
        SimpleAuthenticationInfo info  = new SimpleAuthenticationInfo(user,user.getPassword(),salt, this.getName());
        return info;
    }

    // 测试查看明码加密后的密码
/*        public static void main(String[] args) {
        String hashAlgorithmName = "MD5";
        String credentials = "123";
        int hashIterations = 1024;
        ByteSource credentialsSalt = ByteSource.Util.bytes("王五");
        System.out.println(credentialsSalt);
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj);
    }*/

}
