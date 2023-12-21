package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.impl.AdService;

import javax.servlet.UnavailableException;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления", description = "контроллер для работы с объявлениями")
@RequestMapping("/ads")
public class AdsController {

    private final AdService adService;

    public AdsController(AdService adService) {
        this.adService = adService;
    }

    @Operation(
            summary = "Получение всех объявлений",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Ads.class)
                                    )})})
    @GetMapping
    public ResponseEntity<Ads> getAllAds(){
        return ResponseEntity.ok(adService.getAllAds());
    }

    @Operation(
            summary = "Добавление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = AdDto.class)
                                    )}),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    )})
    @PostMapping
    public ResponseEntity<AdDto> addAd(@RequestParam MultipartFile image,
                                       @RequestParam CreateOrUpdateAd properties,
                                        Authentication authentication){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        return ResponseEntity.ok(adService.addAds(properties, image, authentication, userName));
    }

    @Operation(
            summary = "Получение информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = ExtendedAd.class)
                                    )}),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )})

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAd> getAd(@PathVariable int id){
        return new ResponseEntity<>(adService.getAds(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Удаление объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )})
    @DeleteMapping("/{id}")
    public ResponseEntity<AdDto> deleteAds(@PathVariable int adId,
                                           Authentication authentication)  throws UnavailableException {
        adService.removeAd(adId, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновление информации об объявлении",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = AdDto.class)
                                    )}),
                    @ApiResponse(
                            responseCode = "401",
                            description = " Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )})
    @PatchMapping("/{id}")
    public ResponseEntity<CreateOrUpdateAd> updateAd (@PathVariable int adsId,
                                           @RequestBody CreateOrUpdateAd createOrUpdateAd,
                                           Authentication authentication) throws UnavailableException {
        return ResponseEntity.ok(adService.updateAds(createOrUpdateAd, authentication, adsId ));
    }

    @Operation(
            summary = "Получение объявлений авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Ads.class)
                                    )})})
    @GetMapping("/me")
    public ResponseEntity<Ads> getAdsMe(Authentication authentication) {
        return ResponseEntity.ok(adService.getAdsMe(authentication));
    }

    @Operation(
            summary = "Обновление картинки объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE
                                    )}),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                            ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                            ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                            )})
    @PatchMapping("/{id}/image")
    public ResponseEntity<Ads> updateAdsImage(@RequestParam MultipartFile image,
                                              @PathVariable int id,
                                              Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        adService.uploadImage(id, authentication, image, userName );
        return ResponseEntity.ok().build();
    }
}