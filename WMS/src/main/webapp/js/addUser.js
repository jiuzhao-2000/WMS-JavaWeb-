const code = $("#code");
var isCodeOk = false;
var isPwdOk = false;
code.on("blur", function () {
    const val = $("#code").val();
    if (val == null || val === '') {
        code.next().html("用户名不为空");
        isCodeOk = false;
        return;
    }
    $.ajax({
        type: 'POST',
        url: "/sys/UniqueCodeServlet.do",
        data: {code: val},
        dataType: "json",
        success: function (date) {
            var rst = date.rst;
            if (rst === "t") {
                code.next().html("");
                isCodeOk = true;
            } else {
                code.next().html("用户名重复");
                isCodeOk = false;
            }
        },
        error: function () {
            code.next().html("请求出错！");
            isCodeOk = false;
        }
    })
})
const pwd = $("#pwd");
pwd.on("blur", function () {
    const val = $("#pwd").val();
    if (val == null || val === "") {
        pwd.next().html("密码不能为空");
        isPwdOk = false
    } else {
        pwd.next().html("");
        isPwdOk = true;
    }
})

function isSubmitOk() {
    if (!(isCodeOk && isPwdOk))
        return false;
    const isPowerOk = $("#power1").prop("checked") || $("#power2").prop("checked") || $("#power3").prop("checked") || $("#power4").prop("checked");
    if (isPowerOk)
        return true;
    alert("您至少要选择一项权限");
    return false;
}