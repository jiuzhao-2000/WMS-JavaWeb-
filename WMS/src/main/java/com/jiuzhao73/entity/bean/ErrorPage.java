package com.jiuzhao73.entity.bean;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorPage {
    public static void gotoMethodErrorPage(HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect("/error/notFindMethodError.html");
    }

    public static void gotoPowerErrorPage(HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect("/error/noPowerError.html");
    }

    public static void gotoNoUserErrorPage(HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect("/error/userNotFindError.html");
    }
}
