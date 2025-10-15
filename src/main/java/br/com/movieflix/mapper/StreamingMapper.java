package br.com.movieflix.mapper;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.entity.request.StreamingRequestDto;
import br.com.movieflix.entity.response.StreamingResponseDto;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class StreamingMapper {

    public static Streaming paraEntidade(StreamingRequestDto streamingRequestDto){
        return Streaming
                .builder()
                .nome(streamingRequestDto.nome())
                .build();
    }

    public static StreamingResponseDto paraStreamingResponseDto(Streaming streaming){
        return StreamingResponseDto
                .builder()
                .id(streaming.getId())
                .nome(streaming.getNome())
                .build();
    }
}
