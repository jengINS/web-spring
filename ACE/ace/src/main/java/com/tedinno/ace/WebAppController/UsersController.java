package com.tedinno.ace.WebAppController;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tedinno.ace.Service.AuthenService;
import com.tedinno.ace.Service.UsersService;
import com.tedinno.ace.bind.tbl_authen;
import com.tedinno.ace.bind.tbl_users;
import com.tedinno.ace.repo.CustomRepository;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private AuthenService authenService;

    @Autowired
    private CustomRepository customrepository;

    @GetMapping("/user_list")
    public String userList(HttpSession session, Model model) {
        model.addAttribute("user_list", usersService.getUserList());

        Long s_id = (Long) session.getAttribute("s_id");
        String s_name = (String) session.getAttribute("s_name");
        String isLogin = (String) session.getAttribute("isLogin");

        model.addAttribute("ps_id", s_id);
        model.addAttribute("ps_name", s_name);
        model.addAttribute("isLogin", isLogin);

        return "admin/user_list";
    }

    @GetMapping("/authen_list")
    public String authen_list(HttpSession session, Model model) {
        model.addAttribute("authen_list", authenService.getAllAuthen());

        return "admin/authen_list";
    }

    @GetMapping("/PermissionStatus/{au_id}/{au_status}")
    public String Permission_Status(HttpSession session, @PathVariable(value = "au_id") Long au_id,
            @PathVariable(value = "au_status") String au_status) {

        Object[] updateData = { au_status, au_id };
        customrepository.updateDataCustomStatusUser(updateData);
        // Long s_id = (Long) session.getAttribute("s_id");
        // authenService.Permission_Status(s_id, au_id, au_status);
        return "redirect:/authen_list";
    }

    @GetMapping("/custom-data")
    public ResponseEntity<String> getCustomData(Model model) {
        var oi = customrepository.executeCustomQuery();
        model.addAttribute("ioArray", oi);
        return ResponseEntity.ok("{\"message\": \"Update successful!\"}");
    }

    @PostMapping("/custom_update")
    public ResponseEntity<String> updateData(@RequestBody Object[] updateData) {
        customrepository.updateDataCustom(updateData);
        return ResponseEntity.ok("{\"message\": \"Update successful!\"}");

    }

    @GetMapping("/get-query")
    public ResponseEntity<ArrayList<Object[]>> getData(@RequestParam String name) {
        // Replace this with your actual data retrieval logic
        ArrayList<Object[]> data = customrepository
                .executeCustomQuery("SELECT * FROM `alert_tb` where user_name='" + name + "'");
        // model.addAttribute("ioArrayQ", data);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/UserPermission/{id}")
    // @RequestMapping(value="/UserUpdate/{id}", method = RequestMethod.GET)
    public String permissionForm(@PathVariable(value = "id") long id, Model model) {
        // model.addAttribute("authen_list", authenService.getAllPermission());
        return "admin/user_permission";
    }

    @GetMapping("/adduser")
    public String userform(Model model) {
        tbl_users tbl_users = new tbl_users();
        model.addAttribute("userlist", tbl_users);
        return "admin/user_form";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("Tbl_users") tbl_users tbl_users) {
        usersService.save(tbl_users);
        // return "user_list";
        return "redirect:/user_list";
    }

    @GetMapping("/UserUpdate/{id}")
    // @RequestMapping(value="/UserUpdate/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable(value = "id") long id, Model model) {
        tbl_users Tbl_users = usersService.getById(id);
        model.addAttribute("userlist", Tbl_users);
        String[] asStatus = { "A", "N" };
        model.addAttribute("AsStatus", asStatus);

        return "admin/user_form";
    }

    @GetMapping("/UserView/{id}")
    public String viewForm(@PathVariable(value = "id") long id, Model model) {
        tbl_users Tbl_users = usersService.getById(id);
        model.addAttribute("userview", Tbl_users);
        return "admin/user_view";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        // usersService.displayBookssAndAuthors();
        // List<Object[]> results = usersService.getUserList();
        model.addAttribute("allemplist", new tbl_users());
        return "login";
    }

    private ArrayList<Object> arrayAU = new ArrayList<>();

    @PostMapping("/login")
    public String getLogin(@RequestParam String username, @RequestParam String password, HttpServletRequest request,
            HttpSession session, Model model) {
        var objtbluser = usersService.login(username, password);
        arrayAU = new ArrayList<>();
        // usersService.displayBooksAndAuthors();

        Long id = 0L;
        String fname = "";
        String mail = "";
        for (tbl_users tbl_users : objtbluser) {
            id = tbl_users.getId();
            fname = tbl_users.getFull_name();
            mail = tbl_users.getEmail();
        }
        if (fname != "") {
            model.addAttribute("id", id);
            model.addAttribute("fname", fname);
            model.addAttribute("mail", mail);

            // Long auid = 0L;
            List<Object[]> result = usersService.UserPermission();
            // ArrayList<Object> arrayAU = new ArrayList<>();
            for (Object[] row : result) {
                // Object element = row[0];
                // Long _id = Long.parseLong(row[0].toString());
                Long user_id = Long.parseLong(row[0].toString());
                Long au_id = Long.parseLong(row[1].toString());
                var objtblauthen = authenService.SessionAuthen(au_id);

                for (tbl_authen tbl_authen : objtblauthen) {
                    var auid = tbl_authen.getAu_name();
                    session.setAttribute(auid, auid);
                    String sauid = (String) session.getAttribute(auid);
                    arrayAU.add(sauid);
                    // model.addAttribute(auid, sauid);

                }

            }
            model.addAttribute("sauid", arrayAU);

            // String name = request.getParameter("name");
            session.setAttribute("s_id", id);
            session.setAttribute("s_name", fname);
            session.setAttribute("isLogin", "True");
            session.setAttribute("dd", arrayAU);

            Long s_id = (Long) session.getAttribute("s_id");
            String s_name = (String) session.getAttribute("s_name");
            String isLogin = (String) session.getAttribute("isLogin");

            model.addAttribute("ps_id", s_id);
            model.addAttribute("ps_name", s_name);
            model.addAttribute("isLogin", isLogin);
            return "redirect:/index";

        } else {
            model.addAttribute("msg_login", "Username Or Password Incorrect");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logoutForm(HttpSession session, Model model) {
        Enumeration em = session.getAttributeNames();
        while (em.hasMoreElements()) {
            if ((String) em.nextElement() != "uname") {
                session.removeAttribute((String) em.nextElement());
            }
        }
        return "login";
    }

    @GetMapping("/index")
    public String viewHomePage(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());

        // var ui = arrayAU.get(0);
        for (int t = 0; t < arrayAU.size(); t++) {

            model.addAttribute(arrayAU.get(t).toString(), arrayAU.get(t));
        }

        String s_name = (String) session.getAttribute("s_name");
        String isLogin = (String) session.getAttribute("isLogin");

        model.addAttribute("ps_name", s_name);
        model.addAttribute("isLogin", isLogin);
        return "index";
    }

    // @GetMapping("/custom-data")
    // public ResponseEntity<String> getCustomData(Model model) {
    // var oi = customrepository.executeCustomQuery();
    // model.addAttribute("ioArray", oi);
    // return ResponseEntity.ok("{\"message\": \"Update successful!\"}");
    // }

    @RequestMapping("/navbar")
    public String navbar(HttpSession session, Model model) {

        // var oi = customRepository1.executeCustomQuery();
        // model.addAttribute("dataArray", oi);
        // model.addAttribute("dataArrayCount", oi.size());
        String s_name = (String) session.getAttribute("s_name");
        String isLogin = (String) session.getAttribute("isLogin");

        model.addAttribute("ps_name", s_name);
        model.addAttribute("isLogin", isLogin);
        return "navbar";
    }

    @GetMapping("/projectManagement")
    public String projectManagement(HttpSession session, Model model) {

        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "projectManagement";
    }

    @GetMapping("/createProject")
    public String createProject(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "createProject";
    }

    @GetMapping("/projectDetail")
    public String projectDetail(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "projectDetail";
    }

    @GetMapping("/projectDetail/{id}")
    public String projectDetailid(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "projectDetail";
    }

    @GetMapping("/projectDashboard")
    public String projectDashboard(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "projectDashboard";
    }

    @GetMapping("/boqList")
    public String boqList(HttpSession session, Model model) {
        String s_name = (String) session.getAttribute("s_name");
        String isLogin = (String) session.getAttribute("isLogin");
        String re_newrow = (String) session.getAttribute("result-newrow");
        model.addAttribute("ps_name", s_name);
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("newrow_resual", re_newrow);

        var oi = customrepository.executeCustomQuery(
                " SELECT tbl_boq.*,tbl_users.full_name,status_tb.statusName FROM `tbl_boq`"
                        + " JOIN tbl_users ON tbl_boq.user_update = tbl_users.id"
                        + " JOIN status_tb ON tbl_boq.boq_status = status_tb.id  WHERE active_status = 'A'");
        model.addAttribute("ioArray", oi);

        return "boqList";
    }

    @GetMapping("/newRow")
    public String newRow(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "newRow";
    }

    @PostMapping("/newRow")
    public String newRow(Model model, HttpSession session) {
        session.setAttribute("result-newrow", "closed");
        // usersService.save(tbl_users);
        // return "user_list";
        // for (Object[] itemArray : itemList) {
        // String description = (String) itemArray[0];
        // String cost = (String) itemArray[1];

        // System.out.println("Description: " + description + ", Cost: " + cost);
        // }

        return "redirect:/boq";
    }

    @GetMapping("/boq")
    public String boq(@RequestParam(name = "id", required = false) String id, HttpSession session, Model model) {

        String s_name = (String) session.getAttribute("s_name");
        String isLogin = (String) session.getAttribute("isLogin");
        String re_newrow = (String) session.getAttribute("result-newrow");
        model.addAttribute("ps_name", s_name);
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("newrow_resual", re_newrow);
        if (id != null) {
            ArrayList<Object[]> results = customrepository
                    .executeCustomQuery("SELECT * FROM `tbl_boqdetail` WHERE `BOQnumber` = '" + id + "'");
            model.addAttribute("qArray", results);
        }

        return "boq";
    }

    @PostMapping("/boq")
    public String boq(@RequestBody List<Object[]> itemList) {
        // usersService.save(tbl_users);
        // return "user_list";
        for (Object[] itemArray : itemList) {
            String description = (String) itemArray[0];
            String cost = (String) itemArray[1];

            System.out.println("Description: " + description + ", Cost: " + cost);
        }

        return "boq";
    }

    @GetMapping("/boqAppove")
    public String boqAppove(HttpSession session, Model model) {

        String s_name = (String) session.getAttribute("s_name");
        String isLogin = (String) session.getAttribute("isLogin");
        String re_newrow = (String) session.getAttribute("result-newrow");
        model.addAttribute("ps_name", s_name);
        model.addAttribute("isLogin", isLogin);
        model.addAttribute("newrow_resual", re_newrow);
        return "boqApprove";
    }

    @GetMapping("/supplierManagement")
    public String supplierManagement(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "supplierManagement";
    }

    @GetMapping("/supplierSideNav")
    public String supplierSideNav(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "supplierSideNav";
    }

    @GetMapping("/IOUManagement")
    public String IOUManagement(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "IOUManagement";
    }

    @GetMapping("/iouRequestForm")
    public String iouRequestForm(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "iouRequestForm";
    }

    @GetMapping("/iouView")
    public String iouView(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "iouView";
    }

    @GetMapping("/iouAppove")
    public String iouAppove(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "iouAppove";
    }

    @GetMapping("/iouTravel")
    public String iouTravel(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "iouTravel";
    }

    @GetMapping("/LOGManagement")
    public String LOGManagement(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "LOGManagement";
    }

    @GetMapping("/logDetail")
    public String logDetail(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "logDetail";
    }

    @GetMapping("/createLog")
    public String createLog(HttpSession session, Model model) {
        model.addAttribute("allemplist", usersService.getAllEmployee());
        return "createLog";
    }

    @GetMapping("/account")
    public String account(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "account";
    }

    @GetMapping("/accountSideNav")
    public String accountSideNav(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "accountSideNav";
    }

    @GetMapping("/expense")
    public String expense(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "expense";
    }

    @GetMapping("/income")
    public String income(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "income";
    }

    @GetMapping("/transaction")
    public String transaction(HttpSession session, Model model) {
        // model.addAttribute("allemplist", usersService.getAllEmployee());
        return "transaction";
    }

    @PostMapping("/custom_update_Boq")
    public ResponseEntity<String> custom_update_Boq(@RequestBody Object[][] LupdateData) {

        var resaul = customrepository.custom_update_Boq(LupdateData);
        if (resaul.equals("BOQ DATA ERROR")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + resaul);
        } else if (resaul.equals("DATA ERROR")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + resaul);
        } else
            return ResponseEntity.ok("{\"message\": \"Update successful!\"}");

    }

}
