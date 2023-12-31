import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class workoutReccomender {


    public String reccomend(String username){

        workoutReccomender wr = new workoutReccomender();
        Workout_Model model = Workout_Model.getInstance();
        ArrayList<ArrayList<String>> workouts = model.getWorkouts(username); //changed from list for integration
        ArrayList<String> recent = workouts.get(workouts.size()-1);
        String prompt = "Here was my last workout, complete with the name I assigned it, comments regarding it, and " +
                "each exercise I did with the duration of that exercise: ";
        for(int i=0;i<recent.size();i++){
            prompt += recent.get(i) +",";
        }
        prompt += ". In 100 words or less, what do you recommend I train in my next session, " +
                "based on my last workout?";
        System.out.println(recent);
        System.out.println(prompt);
        String response = wr.chatGPT(prompt);
        return response;
    }
    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
        int maxRetries = 3; // Maximum number of retries
        int retryDelay = 1000; // Initial retry delay in milliseconds

        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                URL obj = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", "Bearer " + apiKey);
                connection.setRequestProperty("Content-Type", "application/json");

                // The request body
                String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
                connection.setDoOutput(true);
                OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
                writer.write(body);
                writer.flush();
                writer.close();

                // Response from ChatGPT
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                StringBuffer response = new StringBuffer();

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                // Calls the method to extract the message.
                return extractMessageFromJSONResponse(response.toString());

            } catch (IOException e) {
                // Retry on IOException
                System.out.println("Error: " + e.getMessage());
                System.out.println("Retry attempt: " + (retry + 1));
                try {
                    // Implement exponential backoff by doubling the delay time on each retry
                    Thread.sleep(retryDelay);
                    retryDelay *= 2;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Return an error message if maxRetries are reached
        return "Error: Maximum number of retries reached. Unable to process the request.";
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }

    }

