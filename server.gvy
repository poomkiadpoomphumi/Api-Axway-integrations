import javax.net.ssl.*
import java.security.cert.X509Certificate
import java.net.HttpURLConnection
import java.net.URL
// Helper method to disable SSL certificate validation
def disableSslVerification() {
    def trustAllCerts = [
        new X509TrustManager() {
            X509Certificate[] getAcceptedIssuers() { null }
            void checkClientTrusted(X509Certificate[] certs, String authType) {}
            void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }
    ] as TrustManager[]

    SSLContext sc = SSLContext.getInstance("TLS")
    sc.init(null, trustAllCerts, new java.security.SecureRandom())
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.socketFactory)

    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
        boolean verify(String hostname, SSLSession session) { true }
    })
}
disableSslVerification() // Disable SSL verification (for development purposes only)

class ApiUtils {
    static String callApiEmployee(String code, String via) {
        try {
            URL apiUrl // Declare apiUrl outside the if conditions

            // Assign the appropriate URL based on the 'via' parameter
            if (via == 'Employee') {
                apiUrl = new URL("https://tso-meeting-room.pttplc.com/data/empdata/empcode/${code}")
            } else if (via == 'Employer') {
                apiUrl = new URL("https://tso-meeting-room.pttplc.com/data/empdata/higherlv/${code}")
            } else {
                println("Invalid 'via' parameter: ${via}")
                return null
            }
            // Make the API request
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection()
            connection.setRequestMethod("GET")
            connection.setRequestProperty("Accept", "application/json")
            // Check if the request was successful
            if (connection.responseCode == 200) {
                // Explicitly set UTF-8 encoding for reading the response
                def response = connection.inputStream.withReader("UTF-8") { reader -> reader.text }
                return response
            } else {
                println("Error: HTTP response code ${connection.responseCode}")
                return null
            }
        } catch (Exception e) {
            println("Exception occurred: ${e.message}")
            return null
        }
    }
}

def code = '600010'
def response = ApiUtils.callApiEmployee(code,'Employee')
println(response)



