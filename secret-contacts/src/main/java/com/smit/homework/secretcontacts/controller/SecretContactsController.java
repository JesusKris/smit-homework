package com.smit.homework.secretcontacts.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.smit.homework.secretcontacts.entity.SecretContactEntity;
import com.smit.homework.secretcontacts.model.ApiResponse;
import com.smit.homework.secretcontacts.model.ErrorResponse;
import com.smit.homework.secretcontacts.model.ProjectLogger;
import com.smit.homework.secretcontacts.model.SuccessResponse;
import com.smit.homework.secretcontacts.service.SecretContactsService;
import java.lang.System.Logger.Level;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Controller class for managing secret contacts through API endpoints.
 * 
 * 
 * <p>
 * <strong>Author:</strong> Robert Kris Laur
 * </p>
 * 
 * @implNote Never allow origin * if it is meant to be a private API
 */
@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE })
@RequestMapping("/api/secret-contacts")
public class SecretContactsController {

        @Autowired
        private ProjectLogger logger;

        @Autowired
        private SecretContactsService secretContactsService;

        @Value("${secret.contacts.api.version}")
        private String apiVersion;


        /**
         * Creates a new secret contact based on the provided parameters.
         *
         * @param request  The HTTP servlet request.
         * @param response The HTTP servlet response.
         * @return ResponseEntity containing the API response.
         */
        @PostMapping
        public ResponseEntity<ApiResponse> newSecretContact(HttpServletRequest request, HttpServletResponse response) {
                logger.log(Level.INFO,
                                "Request received: " + request.getMethod() + " " + getUriWithParameters(request) + " "
                                                + request.getProtocol());

                try {
                        String name = request.getParameter("name");
                        String code = request.getParameter("code");
                        String phone_number = request.getParameter("phone_number");

                        secretContactsService.addNewSecretContact(name, code, phone_number);

                        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK,
                                        "Successfully created a new secret contact",
                                        getUriWithParameters(request), apiVersion);

                        return new ResponseEntity<>(successResponse.toJson(), HttpStatus.OK);

                } catch (Exception e) {
                        logger.log(Level.ERROR, e.getMessage(), e);

                        return new ResponseEntity<>(
                                        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                                                        getUriWithParameters(request),
                                                        apiVersion).toJson(),
                                        HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }


        /**
         * Deletes a secret contact identified by the provided ID.
         *
         * @param request  The HTTP servlet request.
         * @param response The HTTP servlet response.
         * @return ResponseEntity containing the API response.
         */
        @DeleteMapping
        public ResponseEntity<ApiResponse> deleteSecretContact(HttpServletRequest request,
                        HttpServletResponse response) {
                logger.log(Level.INFO,
                                "Request received: " + request.getMethod() + " " + getUriWithParameters(request) + " "
                                                + request.getProtocol());

                try {
                        String secretContactId = request.getParameter("id");

                        secretContactsService.deleteSecretContact(Long.parseLong(secretContactId));

                        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK,
                                        "Successfully deleted a secret contact",
                                        getUriWithParameters(request), apiVersion);

                        return new ResponseEntity<>(successResponse.toJson(), HttpStatus.OK);

                } catch (Exception e) {
                        logger.log(Level.ERROR, e.getMessage(), e);

                        return new ResponseEntity<>(
                                        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                                                        getUriWithParameters(request),
                                                        apiVersion).toJson(),
                                        HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        
        /**
         * Retrieves a list of all secret contacts.
         *
         * @param request  The HTTP servlet request.
         * @param response The HTTP servlet response.
         * @return ResponseEntity containing the API response with secret contacts data.
         */
        @GetMapping
        public ResponseEntity<ApiResponse> getSecretContacts(HttpServletRequest request, HttpServletResponse response) {
                logger.log(Level.INFO,
                                "Request received: " + request.getMethod() + " " + getUriWithParameters(request) + " "
                                                + request.getProtocol());

                try {
                        List<SecretContactEntity> secretContacts = secretContactsService.getSecretContacts();

                        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK,
                                        "Successfully retrieved all secret contacts",
                                        getUriWithParameters(request), apiVersion);

                        successResponse.addData("secret_contacts", secretContacts);

                        return new ResponseEntity<>(successResponse.toJson(), HttpStatus.OK);

                } catch (Exception e) {
                        logger.log(Level.ERROR, e.getMessage(), e);

                        return new ResponseEntity<>(
                                        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                                                        getUriWithParameters(request),
                                                        apiVersion).toJson(),
                                        HttpStatus.INTERNAL_SERVER_ERROR);
                }
        }

        /**
         * Retrieves the URI along with query parameters from the HTTP servlet request.
         *
         * @param request The HTTP servlet request.
         * @return The URI with query parameters.
         */
        private String getUriWithParameters(HttpServletRequest request) {
                return request.getRequestURI() + "?" + request.getQueryString();
        }
}