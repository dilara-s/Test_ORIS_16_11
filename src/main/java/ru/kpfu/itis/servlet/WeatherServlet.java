package ru.kpfu.itis.servlet;

import org.json.JSONObject;
import ru.kpfu.itis.util.HttpClient;
import ru.kpfu.itis.util.HttpClientImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {

    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String API_KEY = "bd5e378503939ddaee76f12ad7a97608";
    private final HttpClient httpClient = new HttpClientImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig().getServletContext().getRequestDispatcher("weather.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityName = req.getParameter("cityName");

        Map<String, String> params = new HashMap<>();
        params.put("q", cityName);
        params.put("appid", API_KEY);
        params.put("units", "metric");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        try {

            String weatherData = httpClient.get(API_URL, headers, params);

            JSONObject json = new JSONObject(weatherData);
            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
            double temperature = json.getJSONObject("main").getDouble("temp");


            req.setAttribute("cityName", cityName);
            req.setAttribute("description", description);
            req.setAttribute("temperature", temperature);

        } catch (Exception e) {
            req.setAttribute("weather", "Ошибка при получении данных о погоде. Пожалуйста, попробуйте еще раз.");
        }

        getServletConfig().getServletContext().getRequestDispatcher("weather.jsp").forward(req, resp);
    }
}
