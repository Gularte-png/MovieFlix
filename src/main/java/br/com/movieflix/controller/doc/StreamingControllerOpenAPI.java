package br.com.movieflix.controller.doc;

import br.com.movieflix.entity.request.StreamingRequestDto;
import br.com.movieflix.entity.response.StreamingResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Streaming", description = "Operações relacionadas a streaming")
public interface StreamingControllerOpenAPI {

    @Operation(summary = "Cria streaming", description = "Realiza a criação de um novo streaming")
    @ApiResponse(responseCode = "200", description = "Streaming criado com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponseDto.class)))
    ResponseEntity<StreamingResponseDto> criarStreaming(StreamingRequestDto streamingRequestDto);

    @Operation(summary = "Busca streaming por ID", description = "Realiza a busca de um streaming por ID")
    @ApiResponse(responseCode = "200", description = "Streaming encontrado com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponseDto.class)))
    ResponseEntity<?> buscarStreamingPorId(Long id);

    @Operation(summary = "Busca todos os streamings", description = "Realiza a busca de todos os streamings")
    @ApiResponse(responseCode = "200", description = "Streamings encontrados com sucesso", content = @Content(schema = @Schema(implementation = StreamingResponseDto.class)))
    ResponseEntity<List<StreamingResponseDto>> buscarTodosStreaming();

    @Operation(summary = "Deleta streaming por ID", description = "Realiza a exclusão de um streaming por ID")
    @ApiResponse(responseCode = "204", description = "Streaming deletado com sucesso")
    ResponseEntity<Void> deletarStreamingPorId(Long id);

}
