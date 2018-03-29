<html>
    <body>
        <h2>Online Shopping Store</h2>
        <hr>
        <form action="VerifyUser" method="get">
            <pre>
            Userid      <input type="text" name="uid"/>
            Password    <input type="password" name="pwd"/>
            Usertype    buyer <input type="radio" name="utype" value="buyer" checked="checked"/>  admin <input type="radio" name="utype" value="admin"/>
            SavePwd     <input type="checkbox" name="save" value="yes"/>
                        <input type="submit" value="Login"/>
            </pre>                        
        </form>
        <hr>
        <a href="registration.jsp">New-User</a>
    </body>
</html>
