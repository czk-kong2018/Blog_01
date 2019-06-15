
$("#btn-submit").click(function(){
    var email=$("#exampleInputEmail1")[0].value;
    var pwd=$("#exampleInputPassword1")[0].value;
    if(email!="".trim()&&pwd!="".trim()){
        $.ajax({
            type: "post",
            url: "http://localhost:8080/Login/doLogin",
            data: JSON.stringify({
                "email":email,
                "pwd":pwd
            }),
            async: false,
            contentType: "application/json;character:utf-8",
            success: function (e) {
	         alert(JSON.stringify(e));
               var user_id=getCookie("user_id");
               var user_name=getCookie("user_name");
               if(user_id!=""&&user_name!=""){

                   $("#login_user").html("<i class=\"fa fa-user-circle\"></i>"+user_name);
                   $("#login_user").attr("href","http://localhost:8080/back/Manager");
                   location.replace(location);
               }
            },
            error:function(response){
                console.log("服务器异常");
            }
        });
    }else{
        alert("邮箱或密码不能为空");
    }
});
function getCookie(cname)
            {
                var name = cname + "=";
                var ca = document.cookie.split(';');
                for(var i=0; i<ca.length; i++)
                {
                    var c = ca[i].trim();
                    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
                }
                return "";
            }
        $(document).ready(function(){
        var user_id=getCookie("user_id");
        var user_name=getCookie("user_name");
        if(user_name!=""&&user_id!=""){
            $("#login_user").html("<i class=\"fa fa-user-circle\"></i>"+user_name);
            $("#login_user").attr("href","http://localhost:8080/back/Manager");
            $("#login_user").attr("targe","new_blank");
        }else{
            $("#login_user").html("<i class=\"fa fa-user-circle\"></i>"+"登录");
            $("#login_user").attr("href","#loginFade");
        } 
    });


//登录失效后 清空cookie
function clearCookie(){
	$.cookie("user_id", "", {expires: -1,path:'/'});
	$.cookie("user_name", "", {expires: -1,path:'/'});
}
