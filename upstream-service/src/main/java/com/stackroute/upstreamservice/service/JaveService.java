package com.stackroute.upstreamservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.dialogflow.v2.*;
import com.google.protobuf.ByteString;

import com.google.protobuf.util.JsonFormat;
import org.springframework.stereotype.Service;
import ws.schild.jave.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class JaveService {
    private static final Logger logger = LoggerFactory.getLogger(JaveService.class);

    private JaveService() {
    }

    public String videoEncoder(File source) throws Exception{
        File target = new File("target.wav");
        AudioAttributes audio = new AudioAttributes();
        audio.setChannels(1);
        audio.setCodec("pcm_s16le");
        audio.setSamplingRate(16000);
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(source), target, attrs);
        JaveService javeService = new JaveService();
        logger.info("Calling dialogflow detectIntent");
        return javeService.detectIntentAudio("videobot-cugcwg","target.wav","123456789","en-IN");
    }
    public String detectIntentAudio(
            String projectId,
            String audioFilePath,
            String sessionId,
            String languageCode)
            throws IOException {
        /** Instantiates a client*/
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            // Set the session name using the sessionId (UUID) and projectID (my-project-id)
            SessionName session = SessionName.of(projectId, sessionId);
            logger.info("Session Path" );

            /** Note: hard coding aud504504504ioEncoding and sampleRateHertz for simplicity.
             // Audio encoding of the audio content sent in the query request.*/
            AudioEncoding audioEncoding = AudioEncoding.AUDIO_ENCODING_LINEAR_16;
            int sampleRateHertz = 16000;

            /** Instructs the speech recognizer how to process the audio content.*/
            InputAudioConfig inputAudioConfig = InputAudioConfig.newBuilder()
                    .setAudioEncoding(audioEncoding) /** audioEncoding = AudioEncoding.AUDIO_ENCODING_LINEAR_16*/
                    .setLanguageCode(languageCode) /** languageCode = "en-IN"*/
                    .setSampleRateHertz(sampleRateHertz)
                    .build();

            /** Build the query with the InputAudioConfig*/
            QueryInput queryInput = QueryInput.newBuilder().setAudioConfig(inputAudioConfig).build();

            /** Read the bytes from the audio file*/
            byte[] inputAudio = Files.readAllBytes(Paths.get(audioFilePath));

            /** Build the DetectIntentRequest*/
            DetectIntentRequest request = DetectIntentRequest.newBuilder()
                    .setSession(session.toString())
                    .setQueryInput(queryInput)
                    .setInputAudio(ByteString.copyFrom(inputAudio))
                    .build();

            /** Performs the detect intent request*/
            DetectIntentResponse response = sessionsClient.detectIntent(request);

            /** Display the query result*/
            QueryResult queryResult = response.getQueryResult();
            logger.info("====================");
            ObjectMapper mapper = new ObjectMapper();
            String msg = queryResult.getQueryTextBytes().toStringUtf8();
            logger.info(msg);
            logger.info("JsonFormat: {}",JsonFormat.printer().print(queryResult.getParametersOrBuilder()));
            return JsonFormat.printer().print(queryResult.getParametersOrBuilder());
        }
    }
}
