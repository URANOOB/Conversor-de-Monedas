//Importar gson
import com.google.gson.Gson;
import com.google.gson.JsonObject;
//Importaciones
import java.util.Scanner;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        Scanner Opcion = new Scanner(System.in);
        Scanner Monto = new Scanner(System.in);
        int Categoria = 0;
        double monto = 0;
        String Divisa = "";
        Gson gson = new Gson();

        while (true) {
            System.out.println("""
                    
                    **************************
                    Selecciona una opción dentro del menú:
                    1 - USD a ARS
                    2 - USD a BRL
                    3 - USD a COP
                    4 - USD a MXN
                    5 - MXN a USD
                    6 - BRL a USD
                    7 - ARS a USD
                    8 - COP a USD
                    9 - Salir
                    **************************
                    """);
            Categoria = Opcion.nextInt();

            if (Categoria == 1 || Categoria == 2 || Categoria == 3 || Categoria == 4) {
                Divisa = "USD";
            } else if (Categoria == 5) {
                Divisa = "MXN";
            } else if (Categoria == 6) {
                Divisa = "BRL";
            } else if (Categoria == 7) {
                Divisa = "ARS";
            } else if (Categoria == 8) {
                Divisa = "COP";
            } else if (Categoria == 9) {
                System.out.println("""
                Gracias por usar el conversor de divisas.
                ¡Vuelva pronto!
                """);
                break;
            }
            else if (Categoria < 1 || Categoria > 9) {
                System.out.println("Por favor selecciona una opción válida");
                continue;
            }

            HttpClient client = HttpClient.newHttpClient();
            //Aquí agregamos la API Key de Exchangerate
            URI Llave = URI.create("https://v6.exchangerate-api.com/v6/3e6670be396646341c40220c/latest/" + Divisa);
            HttpRequest request = HttpRequest.newBuilder().uri(Llave).build();

            try {
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
                JsonObject MonedaObject = jsonObject.getAsJsonObject("conversion_rates");
                System.out.println("Ingresa el monto a convertir: ");
                monto = Monto.nextDouble();
                if (Categoria == 1) {
                    double conversion = monto * MonedaObject.get("ARS").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                } else if (Categoria == 2) {
                    double conversion = monto * MonedaObject.get("BRL").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                } else if (Categoria == 3) {
                    double conversion = monto * MonedaObject.get("COP").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                } else if (Categoria == 4) {
                    double conversion = monto * MonedaObject.get("MXN").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                } else if (Categoria == 5) {
                    double conversion = monto * MonedaObject.get("USD").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                } else if (Categoria == 6) {
                    double conversion = monto * MonedaObject.get("USD").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                } else if (Categoria == 7) {
                    double conversion = monto * MonedaObject.get("USD").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                } else if (Categoria == 8) {
                    double conversion = monto * MonedaObject.get("USD").getAsDouble();
                    System.out.println("La conversión es: " + conversion);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}