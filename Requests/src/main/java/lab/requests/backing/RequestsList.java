package lab.requests.backing;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import jakarta.faces.component.html.HtmlDataTable;
import jakarta.validation.constraints.Size;
import lab.requests.entities.Request;
import lab.requests.data.RequestRepository;

import java.time.LocalDate;
import java.util.List;

@RequestScoped
@Named
public class RequestsList {

    @Inject
    private RequestRepository requestRepository;

    @Size(min = 3, max = 60, message = "{request.size}")
    private String newRequest;

    private HtmlDataTable requestsDataTable;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Transactional
    public String addRequest() {
        Request req = new Request();
        req.setRequestDate(LocalDate.now());
        req.setRequestText(newRequest);

        requestRepository.create(req);

        setNewRequest("");

        return "requestsList?faces-redirect=true";
    }

    @Transactional
    public String deleteRequest() {
        Request req = (Request) requestsDataTable.getRowData();
        requestRepository.remove(req);

        return "requestsList?faces-redirect=true";
    }


    public String getNewRequest() {
        return newRequest;
    }

    public void setNewRequest(String newRequest) {
        this.newRequest = newRequest;
    }

    public HtmlDataTable getRequestsDataTable() {
        return requestsDataTable;
    }

    public void setRequestsDataTable(HtmlDataTable requestsDataTable) {
        this.requestsDataTable = requestsDataTable;
    }
}