<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div>
    <label id="username" username="<sec:authentication property='name'/>" hidden="hidden"></label>
    <h5 id="welcome"></h5>
</div>

<div style="text-align: right">
    <a href="${pageContext.request.contextPath}/j_spring_security_logout">登出</a>
</div>
<hr>
<a href="${pageContext.request.contextPath}/index.html">go -> 首页</a>
<hr>
