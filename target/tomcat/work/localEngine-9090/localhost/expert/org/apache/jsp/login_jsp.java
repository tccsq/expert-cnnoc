package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/backend/common/taglib.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/backend/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<META content=\"text/html; charset=UTF-8\" http-equiv=Content-Type>\r\n");
      out.write("<LINK rel=stylesheet type=text/css href=\"script/assets/main.css\">\r\n");
      out.write("<LINK rel=stylesheet type=text/css href=\"script/assets/easyui.css\">\r\n");
      out.write("<LINK rel=stylesheet type=text/css href=\"script/assets/icon.css\">\r\n");
      out.write("<LINK rel=stylesheet type=text/css href=\"script/assets/prettify.css\">\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".item-img {\r\n");
      out.write("\tdisplay: inline-block;\r\n");
      out.write("\tvertical-align: middle;\r\n");
      out.write("\twidth: 16px;\r\n");
      out.write("\theight: 16px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".item-text {\r\n");
      out.write("\tdisplay: inline-block;\r\n");
      out.write("\tvertical-align: middle;\r\n");
      out.write("\tpadding: 3px 0 3px 3px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<SCRIPT type=text/javascript src=\"script/assets/prettify.js\"></SCRIPT>\r\n");
      out.write("<SCRIPT type=text/javascript src=\"script/assets/jquery-1.8.0.min.js\"></SCRIPT>\r\n");
      out.write("<SCRIPT type=text/javascript src=\"script/assets/jquery.easyui.min.js\"></SCRIPT>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction reloadcheckcode(img) {\r\n");
      out.write("\t\timg.src = \"LoginServlet?method=checkcode&temp=”\" + Math.random();\r\n");
      out.write("\t}\r\n");
      out.write("\tfunction login() {\r\n");
      out.write("\t\tvar username = $('#username').attr('value');\r\n");
      out.write("\t\tvar pwd = $('#pwd').attr('value');\r\n");
      out.write("\t\t$('#errormsg').text(\"sdfds\");\r\n");
      out.write("\t\tif (username != \"\" && pwd != \"\") {\r\n");
      out.write("\t\t\t$('#errormsg').text(\"\");\r\n");
      out.write("\t\t\t//跳转\r\n");
      out.write("\t\t\twindow.location = \"main.html\"\r\n");
      out.write("\t\t} else if (username != \"user\") {\r\n");
      out.write("\t\t\t$('#errormsg').text(\"\");\r\n");
      out.write("\t\t\t$('#errormsg').text(\"用户名错误\");\r\n");
      out.write("\t\t} else if (pwd != \"123\") {\r\n");
      out.write("\t\t\t$('#errormsg').text(\"\");\r\n");
      out.write("\t\t\t$('#errormsg').text(\"密码错误\");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"dlg\" class=\"easyui-dialog\"\r\n");
      out.write("\t\tstyle=\"width: 500px; height: 300px; padding: 10px 30px;\" title=\"用户登录\"\r\n");
      out.write("\t\tbuttons=\"#dlg-buttons\">\r\n");
      out.write("\t\t<div style=\"padding-left: 50px;\">\r\n");
      out.write("\t\t\t<h2>专家系统</h2>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<form id=\"ff\" method=\"post\" style=\"padding: 5px 50px;\">\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>用户名:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"username\" id=\"username\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"width: 150px;\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>密码:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"pwd\" id=\"pwd\"\r\n");
      out.write("\t\t\t\t\t\tstyle=\"width: 150px;\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>角色:</td>\r\n");
      out.write("\t\t\t\t\t<td><select name=\"role\" id=\"role\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"1\">普通员工</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"2\">管理员</option>\r\n");
      out.write("\t\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>验证码:</td>\r\n");
      out.write("\t\t\t\t\t<td><input name=\"checkcode\" type=\"text\" id=\"checkcode\"\r\n");
      out.write("\t\t\t\t\t\tmaxlength=\"4\" class=\"chknumber_input\" /> <img\r\n");
      out.write("\t\t\t\t\t\tsrc=\"LoginServlet?method=checkcode\" id=\"safecode\"\r\n");
      out.write("\t\t\t\t\t\tonclick=\"reloadcheckcode(this)\" title=\"如果看不清，请点击本图片换一张\" /></td>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<span id=\"errormsg\" style=\"color: red\"></span>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"dlg-buttons\" style=\"padding-right: 30px;\">\r\n");
      out.write("\t\t<a href=\"#\" class=\"easyui-linkbutton\" iconCls=\"icon-ok\"\r\n");
      out.write("\t\t\tonclick=\"login()\">确定</a> <a href=\"#\" class=\"easyui-linkbutton\"\r\n");
      out.write("\t\t\ticonCls=\"icon-cancel\" onclick=\"javascript:$('#dlg').dialog('close')\">取消</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
