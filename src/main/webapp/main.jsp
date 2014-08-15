<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/backend/common/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/backend/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD><TITLE>专家库系统</TITLE>
    <META content="text/html; charset=UTF-8" http-equiv=Content-Type>
    <LINK rel=stylesheet type=text/css href="script/assets/main.css">
    <LINK rel=stylesheet type=text/css href="script/assets/easyui.css">
    <LINK rel=stylesheet type=text/css href="script/assets/icon.css">
    <LINK rel=stylesheet type=text/css href="script/assets/prettify.css">

    <SCRIPT type=text/javascript src="script/assets/prettify.js"></SCRIPT>
    <SCRIPT type=text/javascript src="script/assets/jquery-1.8.0.min.js"></SCRIPT>

    <SCRIPT type=text/javascript src="script/assets/jquery.easyui.min.js"></SCRIPT>

    <SCRIPT type=text/javascript>
		
        function open1(page) {
			$("#rightFrame").attr("src", page);
        }
    </SCRIPT>


</HEAD>
<BODY style="TEXT-ALIGN: left" class=easyui-layout>
<DIV style="TEXT-ALIGN: center; BACKGROUND: #666" border="false" region="north">
    <DIV id=header-inner>
        <TABLE style="WIDTH: 100%" cellSpacing=0 cellPadding=0>
            <TBODY>
            <TR>
                <TD style="WIDTH: 20px" rowSpan=2></TD>
                <TD style="HEIGHT: 52px">
                    <DIV style="COLOR: #fff; FONT-SIZE: 22px; FONT-WEIGHT: bold"><A
                            style="COLOR: #fff; FONT-SIZE: 22px; FONT-WEIGHT: bold; TEXT-DECORATION: none"
                            href="#">专家库</A></DIV>
                </TD>
                <TD style="TEXT-ALIGN: right; PADDING-RIGHT: 5px; VERTICAL-ALIGN: bottom">

                </TD>
            </TR>
            </TBODY>
        </TABLE>
    </DIV>
</DIV>
<DIV style="PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; WIDTH: 250px; PADDING-RIGHT: 5px; PADDING-TOP: 5px"
        title=主菜单 region="west" split="true">
    <UL class=easyui-tree>

        <LI iconCls="icon-gears"><a class="e-link" onclick="open1('expertManage.html')"
                                    href="#">专家管理</a></LI>
		<LI iconCls="icon-gears"><a class="e-link" onclick="open1('projectManage.html')"
                                    href="#">项目管理</a></LI>
        <LI iconCls="icon-gears"><a class="e-link" onclick="open1('expertGrade.html')"
                                    href="#">评审评分</a></LI>
		<LI iconCls="icon-gears"><a class="e-link" onclick="open1('expertStat.html')"
                                    href="#">评分统计</a></LI>
    </UL>
</DIV>
<DIV region="center">
    <DIV id=tt class=easyui-tabs border="false" plain="true" fit="true">
        <DIV id="welcome" title=welcome style="text-align: center">
			<iframe height="100%" width="100%" frameborder="0" frameborder="0" src="right.jsp" name="rightFrame" id="rightFrame" title="rightFrame"></iframe>
        </DIV>
    </DIV>
</DIV>
</BODY>
</HTML>
