package limit.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import limit.exception.BankLimitNotFoundException;
import limit.model.BankLimitDTO;
import limit.service.BankLimitService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.List;

@Path("/limits/bank")
public class BankLimitController {
    private final BankLimitService bankLimitService;

    @Inject
    public BankLimitController(BankLimitService bankLimitService) {
        this.bankLimitService = bankLimitService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Get All Bank Limits"
    )
    @APIResponses({
           @APIResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(allOf = BankLimitDTO.class))),
           @APIResponse(responseCode = "302", description = "No bank limit available")
    })

    public List<BankLimitDTO> getBankLimits() {
        return bankLimitService.getBankLimits();
    }

    @GET
    @Path("/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Get Bank Limit for the specific product"
    )
    @APIResponses({
          @APIResponse(responseCode = "200",
                       description = "Retrieve the bank limit for the specific product",
                       content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(anyOf = BankLimitDTO.class))),
          @APIResponse(responseCode = "302", description = "There is no bank limit found for the specific product")
      }
    )
    public BankLimitDTO getBankLimitByProductCode(@PathParam("productCode") String productCode) throws BankLimitNotFoundException {
        return bankLimitService.getBankLimitByProductCode(productCode);
    }
}
