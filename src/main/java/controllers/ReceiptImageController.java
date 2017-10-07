package controllers;

import api.ReceiptSuggestionResponse;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import java.util.Scanner;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Collections;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.hibernate.validator.constraints.NotEmpty;

import static java.lang.System.out;

@Path("/images")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptImageController {
    private final AnnotateImageRequest.Builder requestBuilder;



    public ReceiptImageController() {
        // DOCUMENT_TEXT_DETECTION is not the best or only OCR method available
        Feature ocrFeature = Feature.newBuilder().setType(Feature.Type.TEXT_DETECTION).build();
        this.requestBuilder = AnnotateImageRequest.newBuilder().addFeatures(ocrFeature);

    }

    public String getTrueValue(String data) {
        if (data.contains("null")) {
            data = data.replace("null", "");
        }
        return data;
    }

    public BigDecimal getTrueAmount(String data) {
        BigDecimal h = new BigDecimal("2");
        Integer n = 0;
//        while (s.hasNext()) {
//            if (s.hasNextInt()) {
//                System.out.println(s.nextInt());
//                n = s.nextInt();
//            }
//        }
        if (data.contains("$")) {
            data = data.replace("$", "");
            h = new BigDecimal(data);
        } else {
            h = new BigDecimal(data);
        }
        return h;
    }

    /**
     * This borrows heavily from the Google Vision API Docs.  See:
     * https://cloud.google.com/vision/docs/detecting-fulltext
     *
     * YOU SHOULD MODIFY THIS METHOD TO RETURN A ReceiptSuggestionResponse:
     *
     * public class ReceiptSuggestionResponse {
     *     String merchantName;
     *     String amount;
     * }
     */
    @POST
    public ReceiptSuggestionResponse parseReceipt(@NotEmpty String base64EncodedImage) throws Exception {
        Image img = Image.newBuilder().setContent(ByteString.copyFrom(Base64.getDecoder().decode(base64EncodedImage))).build();
        AnnotateImageRequest request = this.requestBuilder.setImage(img).build();

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse responses = client.batchAnnotateImages(Collections.singletonList(request));
            AnnotateImageResponse res = responses.getResponses(0);

            String merchantName = null;
            BigDecimal amount = null;
            String value = null;
            String[] wtg = null;
            String wtg_1 = null;

            // Your Algo Here!!
            // Sort text annotations by bounding polygon.  Top-most non-decimal text is the merchant
            // bottom-most decimal text is the total amount
            for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                value += annotation.getDescription();
//                System.out.printf(annotation.getDescriptions());
                // take first line of first line = merchantName
                // if it is a number, doubleparse number -> add try catch, if it is a number remove $ sign (replace)

//                out.printf("Position : %s\n", annotation.getBoundingPoly());
//                out.printf("Text: %s\n", annotation.getDescription());
            }
            wtg = value.split("\n");
            wtg_1 = wtg[0];
//            System.out.println(wtg.length);
//            System.out.println(wtg_1);
            String lastOne = wtg[wtg.length-5];
//            System.out.println(lastOne);
//            System.out.println(lastOne.length());

            merchantName = getTrueValue(wtg_1);
            amount = getTrueAmount(lastOne);
            //TextAnnotation fullTextAnnotation = res.getFullTextAnnotation();
            return new ReceiptSuggestionResponse(merchantName, amount);
        }
    }
}
