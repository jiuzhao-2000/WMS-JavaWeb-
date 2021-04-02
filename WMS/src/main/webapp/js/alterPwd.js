const oldPwd = $("#oldPwd");
const newPwd = $("#newPwd");
const again = $("#again");
let isOldPwdTrue = false;
let isTwoNewPwdSame = false;
/*当旧密码控件失去焦点*/
oldPwd.on("blur", function () {
        var pwd = $("#oldPwd").val();
        if (pwd == null || pwd === '') {
            oldPwd.next().html("密码不为空！");
            isOldPwdTrue = false;
            return;
        }
        $.ajax({
            type: 'GET',
            url: "alterPwd.do",
            data: {method: "confirmPwd", oldPwd: pwd},
            dataType: "json",
            //请求成功
            success: function (date) {
                switch (date.rst) {
                    case "t":
                        oldPwd.next().html("密码正确！");
                        isOldPwdTrue = true;
                        break;
                    case "f":
                        oldPwd.next().html("密码错误！");
                        isOldPwdTrue = false;
                        break;
                    default:
                        oldPwd.next().html("出现奇怪的错误请与管理员联系！");
                        isOldPwdTrue = false;
                        break;
                }
            },
            //请求出错
            error: function () {
                oldPwd.next().html("请求出错！");
                isOldPwdTrue = false;
            }
        })
    }
).on("focus", function () {
    oldPwd.next().html("请输入密码！");
    isOldPwdTrue = false;
})
/*again失去焦点*/
again.on("blur", function () {
    if (again.val() == null || again.val() === '') {
        again.next().html("密码不为空！");
        isTwoNewPwdSame = false;
    } else if (again.val() === newPwd.val()) {
        again.next().html("");
        newPwd.next().html("");
        isTwoNewPwdSame = true;
    } else {
        again.next().html("俩次密码不一致！");
        isTwoNewPwdSame = false;
    }
}).on("focus", function () {
    again.next().html("再一次输入新密码！");
    isTwoNewPwdSame = false;
})
/*监听newPwd，对比俩次密码*/
newPwd.on("blur", function () {
    if (newPwd.val() == null || newPwd.val() === '') {
        newPwd.next().html("密码不为空！");
        newPwd.next().cssText = 'color:red';
        isTwoNewPwdSame = false;
    } else if (again.val() === newPwd.val()) {
        newPwd.next().html("");
        again.next().html("");
        isTwoNewPwdSame = true;
    } else {
        newPwd.next().html("俩次密码不一致！");
        isTwoNewPwdSame = false;
    }
}).on("focus", function () {
    newPwd.next().html("请输入新密码！");
    isTwoNewPwdSame = false;
})

//点击提交按钮
function onClickSubmit() {
    if (isOldPwdTrue && isTwoNewPwdSame) {
        var ok = confirm("您确认要修改密码吗？");
        console.log(ok);
        return ok;
    }
    return false;
}