<jsp:include page="includes/head.jsp"/>

	<p style="color:red">${mensaje}</p>
	
	<form action="login" method="post">
		
		<input type="text" name="nombre" placeholder="Escribe Tu Usuario" required autofocus>
		<br>
		<input type="password" name="password" placeholder="Contrase�a" required >
		<br>
		<input type="submit" value="Iniciar Sesi�n">
	
	</form>

<jsp:include page="includes/footer.jsp"/>	