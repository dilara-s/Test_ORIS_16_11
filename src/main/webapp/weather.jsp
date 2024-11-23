<!DOCTYPE html>
<html>
<head>
  <title>Weather App</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $('#weatherForm').submit(function(event) {
        event.preventDefault();

        var cityName = $('#cityName').val();

        $.ajax({
          url: 'weather',
          type: 'POST',
          data: { cityName: cityName },
          success: function(response) {
            if (response.error) {
              $('#weatherResult').html('<p>' + response.error + '</p>');
            } else {
              $('#weatherResult').html('<p>Город: ' + response.cityName + '</p>' +
                      '<p>Описание: ' + response.description + '</p>' +
                      '<p>Температура: ' + response.temperature + '°C</p>');
            }
          },
          error: function() {
            $('#weatherResult').html('<p>Ошибка при получении данных о погоде. Пожалуйста, попробуйте еще раз.</p>');
          }
        });
      });
    });
  </script>
</head>
<body>
<h1>Weather App</h1>
<form id="weatherForm">
  <label for="cityName">Введите название города:</label>
  <input type="text" id="cityName" name="cityName" required>
  <button type="submit">Получить погоду</button>
</form>
<div id="weatherResult"></div>
</body>
</html>
