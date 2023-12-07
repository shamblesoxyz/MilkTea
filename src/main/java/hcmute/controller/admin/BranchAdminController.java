package hcmute.controller.admin;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import hcmute.entity.BranchEntity;
import hcmute.entity.CityEntity;
import hcmute.model.BranchModel;
import hcmute.service.IBranchService;

import javax.validation.Valid;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("admin/branch")
public class BranchAdminController {

    @Autowired
    private IBranchService branchService;

    @GetMapping("")
    public String indexViewBranch(ModelMap model) {
        List<BranchEntity> branch = branchService.findAll();
        model.addAttribute("branch", branch);  // Updated attribute name to "branches"
        return "admin/view/view-branch";
    }

    @GetMapping("add")
    public String add(ModelMap model) {
        BranchModel branch = new BranchModel();
        branch.setIsEdit(false);
        model.addAttribute("branch", branch);
        return "admin/customize/customize-branch";
    }
    
    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, @Valid @ModelAttribute("branch") BranchModel branch, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("admin/customize/customize-branch");
        }
        if (branch != null) {
            BranchEntity entity = new BranchEntity();
            if (branch.getIdBranch() != null) {
                entity.setIdBranch(branch.getIdBranch());
            }
            if (branch.getName() != null) {
                entity.setName(branch.getName());
            }
            if (branch.getAddressDetail() != null) {
                entity.setAddressDetail(branch.getAddressDetail());
            }
            if (branch.getOpentime() != null) {
                entity.setOpentime(branch.getOpentime());
            }
            if (branch.getImage() != null) {
                entity.setImage(branch.getImage());
            }
            if (branch.getDescription() != null) {
                entity.setDescription(branch.getDescription());
            }
            branchService.save(entity);
            String message = branch.getIsEdit() ? "Branch đã được cập nhật thành công" : "Branch đã được thêm thành công";
            model.addAttribute("message", message);
        } else {
            model.addAttribute("message", "Không thể lưu Branch với dữ liệu null");
        }

        return new ModelAndView("redirect:/admin/branch", model);
    }

    @GetMapping("edit/{idBranch}")
    public ModelAndView edit(ModelMap model, @PathVariable("idBranch") int idBranch) {
        Optional<BranchEntity> opt = branchService.findById(idBranch);
        BranchModel branch = new BranchModel();
        if (opt.isPresent()) {
            BranchEntity entity = opt.get();
            BeanUtils.copyProperties(entity, branch);
            branch.setIsEdit(true);
            model.addAttribute("branch", branch);
            return new ModelAndView("admin/customize/customize-branch", model);
        }

        model.addAttribute("message", "Branch không tồn tại");
        return new ModelAndView("forward:/admin/branch", model);
    }

    @GetMapping("delete/{idBranch}")
    public ModelAndView delete(ModelMap model, @PathVariable("idBranch") int idBranch) {
        branchService.deleteById(idBranch);
        model.addAttribute("message", "Branch đã xóa thành công");
        return new ModelAndView("forward:/admin/branch", model);
    }
}
