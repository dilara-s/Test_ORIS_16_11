<!DOCTYPE html>
<html>
<head>
  <title>Weather Information</title>
</head>
<body>
<h1>Weather Information</h1>
<form action="weather" method="post">
  <label for="city">Enter City Name:</label>
  <input type="text" id="city" name="city" required>
  <button type="submit">Get Weather</button>
</form>

<%
  String weatherInfo = (String) request.getAttribute("weatherInfo");
  if (weatherInfo != null) {
%>
<h2>Weather Information for <%= request.getAttribute("city") %></h2>
<p><%= weatherInfo %></p>
<%
  }
%>
</body>
</html>
