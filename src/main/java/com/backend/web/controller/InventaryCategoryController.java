package com.backend.web.controller;



import com.backend.domain.service.Impl.InventoryCategoryServiceImpl;
import com.backend.web.dto.Inventory.InventoryDTO;
import com.backend.web.dto.InventoryCategory.InventoryCategoryDTO;
import com.backend.web.dto.InventoryCategory.ResponseCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/inventoryCategory")
public class InventaryCategoryController {

    @Autowired
    private InventoryCategoryServiceImpl inventaryCategoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseCreateDTO> createInventoryCategory(@RequestBody InventoryCategoryDTO InventoryCategoryDTO) throws Exception {
        return ResponseEntity.ok(ResponseCreateDTO
                .builder()
                .getInventoryCategoryDTO(this.inventaryCategoryService.createInventoryCategory(InventoryCategoryDTO))
                .build());
    }

    @GetMapping("/{idInventory}")
    public ResponseEntity<InventoryCategoryDTO> getInventoryById(@PathVariable Integer idInventory) throws Exception {
        InventoryCategoryDTO inventoryCategoryDTO = inventaryCategoryService.getCategoryById(idInventory);
        InventoryCategoryDTO newInventoryDTO = inventoryCategoryDTO.toBuilder().build();
        return ResponseEntity.ok(newInventoryDTO);
    }
}
