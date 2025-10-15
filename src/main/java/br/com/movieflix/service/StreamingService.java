package br.com.movieflix.service;

import br.com.movieflix.entity.Streaming;
import br.com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<Streaming> buscarTodosStreaming(){
        return streamingRepository.findAll();
    }

    public Streaming salvarStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> buscarStreamingPorId(Long id){
        return streamingRepository.findById(id);
    }

    public void deletarStreamingPorId(Long id){
        streamingRepository.deleteById(id);
    }
}
