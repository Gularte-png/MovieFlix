package br.com.movieflix.controller;

import br.com.movieflix.controller.doc.StreamingControllerOpenAPI;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.entity.request.StreamingRequestDto;
import br.com.movieflix.entity.response.StreamingResponseDto;
import br.com.movieflix.mapper.StreamingMapper;
import br.com.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/streaming")
public class StreamingController implements StreamingControllerOpenAPI {

    private final StreamingService streamingService;

    @PostMapping()
    public ResponseEntity<StreamingResponseDto> criarStreaming(@RequestBody StreamingRequestDto streamingRequestDto) {
        Streaming newStreaming = StreamingMapper.paraEntidade(streamingRequestDto);
        Streaming streaming = streamingService.salvarStreaming(newStreaming);
        return ResponseEntity.status(HttpStatus.OK).body(StreamingMapper.paraStreamingResponseDto(streaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarStreamingPorId(@PathVariable Long id) {
        return streamingService.buscarStreamingPorId(id)
                .map(streaming -> ResponseEntity.status(HttpStatus.OK).body(StreamingMapper.paraStreamingResponseDto(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<StreamingResponseDto>> buscarTodosStreaming() {
        List<StreamingResponseDto> streamings = streamingService.buscarTodosStreaming().stream().map(StreamingMapper::paraStreamingResponseDto).toList();
        return !streamings.isEmpty() ? ResponseEntity.ok(streamings) : ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarStreamingPorId(@PathVariable Long id) {
        streamingService.deletarStreamingPorId(id);
        return ResponseEntity.noContent().build();
    }
}
