package com.backend.web.controller;



import com.backend.domain.service.Impl.InventoryCategoryServiceImpl;
import com.backend.web.dto.Inventory.GetInventoryDTO;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.Inventory.ResponseInventoryDTO;
import com.backend.web.dto.InventoryCategory.GetInventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.ResponseCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/inventoryCategory")
public class InventaryCategoryController {

    @Autowired
    private InventoryCategoryServiceImpl inventaryCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseCommentDTO> createInventoryCategory(@RequestBody InventoryCategoryDTO InventoryCategoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseCommentDTO
                .builder()
                .getInventoryCategoryDTO(this.inventaryCategoryService.createInventoryCategory(InventoryCategoryDTO))
                .build());
    }

    @PostMapping ("/update")
    public ResponseEntity<ResponseCommentDTO> updateInventory(@RequestBody InventoryCategoryDTO inventoryCategoryDTO, @RequestParam Integer inventoryId) throws Exception {
        GetInventoryCategoryDTO updatedInventory = inventaryCategoryService.updateInventoryCategory(inventoryCategoryDTO, inventoryId);
        ResponseCommentDTO response = ResponseCommentDTO.builder()
                .getInventoryCategoryDTO(updatedInventory)
                .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{idInventory}")
    public ResponseEntity<InventoryCategoryDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        InventoryCategoryDTO inventoryCategoryDTO = inventaryCategoryService.getCategoryById(idInventory);
        InventoryCategoryDTO newInventoryDTO = inventoryCategoryDTO.toBuilder().build();
        return ResponseEntity.ok(newInventoryDTO);
    }
}
