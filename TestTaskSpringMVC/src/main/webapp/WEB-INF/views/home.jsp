<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<meta name='viewport' content="width=device-width, initial-scale=1" />
	<script data-turbolinks-track="true" src="/test/js/goertzel.js"></script>
<script data-turbolinks-track="true" src="/test/js/dtmf.js"></script>
<script data-turbolinks-track="true" src="/test/js/dtmf_decoder.js"></script>
</head>
<body>
    <div id="output"></div>
<h1> 
	Это тестовое приложение для демонстрации интеграции Spring MVC, Spring Security, Apache Tiles и ORM на примере Hibernate.

</h1>
    <p><h3>Приложение позволяет создавать, удалять и редактировать какие-то абстрактные задачи, а так же просматривать их списки и значение аттрибутов.</h3>
    </p> 
    <p>
    <h4>
	<p>
	    Приложение позволяет создавать, удалять и редактировать какие-то абстрактные задачи, а так же просматривать их списки и значение аттрибутов.
У пользователей приложения есть две роли: "администратор" и "пользователь".
	</p>
	<ul>
        <li>
            Неавторизованые пользователи могут лиш просматривать список задач
        </li>
        <li>
            Администратор может ограниченно редактировать (не может менять статус задачи) , просматривать и удалять задачи
        </li>
	<li>
            "Обычный" пользователь, может просматривать и добавлять задачи
        </li>
	<li>
            Пользователь имеющий роли "администратора" и "обычного" пользователя не имеет ограничения в своих действиях
        </li>	
        </ul>
	<br/>
	<p>
	    В системе есть три пользователя (пароль совпадает с логином) с такими правами:
	</p>
	<ul>
	    <li>keith - "обычный" пользователь</li>
	    <li>keri - не имеет ограничений (опладает правами и администратора и пользователя системы)</li>
	    <li>admin - администратор</li>
	</ul>    
    </h4>
    </p>
 
</body>
</html>
