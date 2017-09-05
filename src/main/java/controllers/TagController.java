package controllers;

import api.CreateReceiptRequest;
import api.ReceiptResponse;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/tags/{tag}")
    public String toggleTag(@PathParam("tag") String tagName, int receiptID) {
        // check if receipt exists
        boolean idExists = tags.receiptExist(receiptID, tagName);
        if (idExists) {
            // id exists true, delete
            tags.delete(receiptID, tagName);
        } else {
            // insert
            tags.insert(receiptID, tagName);
        }
        return "";
    }

    @GET
    @Path("/tags")
    public List<ReceiptResponse> getReceiptsWithT() {
        List<ReceiptsRecord> receiptRecords = tags.getReceiptsWithTags();
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }

    @GET
    @Path("/tags/{tag}")
    public List<ReceiptResponse> getReceiptsByTagCategory(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = tags.getReceiptsByTagCat(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }
}
