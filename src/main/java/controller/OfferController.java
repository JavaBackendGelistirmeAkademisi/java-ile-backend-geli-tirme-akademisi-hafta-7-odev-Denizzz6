package controller;

import exception.WrongArgumentException;
import jakarta.transaction.Transactional;
import model.Offer;
import model.Project;
import model.ProjectStatus;
import model.request.OfferRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.OfferService;
import service.ProjectService;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/offer")
public class OfferController {
    private final OfferService offerService;
    private final ProjectService projectService;
    @Autowired
    public OfferController(OfferService offerService,ProjectService projectService){
        this.offerService=offerService;
        this.projectService=projectService;
    }

    @PostMapping("/offer/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public Offer addOffer(@PathVariable Long id,@Valid @RequestBody OfferRequestDTO offerRequestDTO){
        Project project = projectService.getProjectById(id);

        if (ProjectStatus.COMPLETED.equals(project.getSituation())) {
            throw new IllegalStateException("Tamamlanan projeye yeni teklif verilemez.");
        }
        Offer offer=new Offer();
        offer.setName(offerRequestDTO.getName());
        offer.setPrice(offerRequestDTO.getPrice());
        offer.setTerms(offerRequestDTO.getTerms());

        if (offer.getPrice() < 0) {
            throw new WrongArgumentException("Price cannot be negative");
        }
        return offerService.addOffer(offer);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void deleteOffer(@PathVariable Long id){
        offerService.deleteOffer(id);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public Offer updateOffer(@PathVariable Long id, @Valid @RequestBody OfferRequestDTO offerRequestDTO) throws ChangeSetPersister.NotFoundException {
        Offer offer = offerService.findById(id);
        if (offer == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        offer.setName(offerRequestDTO.getName());
        offer.setPrice(offerRequestDTO.getPrice());
        offer.setTerms(offerRequestDTO.getTerms());
        return offerService.updateOffer(offer);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<Offer> getAllOffer(){
        return offerService.getAllOffer();
    }
}
