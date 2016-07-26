<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="hotshare" uri="http://www.hotshare.com.cn/civil"%>
<script>
	window.onload = function() {
		if (top.location.href != "${basePath}/admin/main/index") {
			top.location.href = "${basePath}/admin/main/index";
		}
	};
</script>