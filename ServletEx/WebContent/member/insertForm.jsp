<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�ű�ȸ�� ���</title>
</head>
<body>
	<form action="insert.do" method="post">
		<table>
			<tr>
				<td>I D:</td>
				<td><input type="text" name="memId" value=""></td>
			</tr>
			<tr>
				<td>�̸�:</td>
				<td><input type="text" name="memName" value=""></td>
			</tr>
			<tr>
					<td>��ȭ��ȣ:</td>
				<td><input type="text" name="memTel" value=""></td>
			</tr>
			<tr>
				<td>�� ��:</td>
				<td><textarea rows="5" col="6" name="memAddr" value=""></textarea></td>
			</tr>
	</table>
	<button type="submit" value="ȸ�����">ȸ�� ���</a></button>		
	</form>

</body>
</html>