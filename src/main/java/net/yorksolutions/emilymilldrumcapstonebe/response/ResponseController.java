package net.yorksolutions.emilymilldrumcapstonebe.response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/response")
@CrossOrigin
public class ResponseController {

    ResponseService responseService;
    public ResponseController(ResponseService responseService){
        this.responseService = responseService;
    }

    @PostMapping
    public Response create(@RequestBody ResponseDTO requestDTO){
        return this.responseService.create(requestDTO);
    }

}
