package limit.exception.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import limit.exception.BankLimitNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class BankLimitNoFoundExceptionHandler implements ExceptionMapper<BankLimitNotFoundException> {
    @Override
    public Response toResponse(BankLimitNotFoundException bankLimitNotFoundException) {
        return Response.status(Response.Status.BAD_REQUEST).entity(newErrorReport(Response.Status.BAD_REQUEST, bankLimitNotFoundException)).build();
    }

    private Map<String, Object> newErrorReport(Response.Status status, BankLimitNotFoundException bankLimitNotFoundException) {
        var errorReport = new HashMap<String, Object>();
        errorReport.put("status", status);
        errorReport.put("error-message", bankLimitNotFoundException.getMessage());
        return errorReport;
    }
}
