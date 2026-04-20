package com.upeu.categorias.service;

import com.upeu.categorias.dto.CategoriaDTO;
import com.upeu.categorias.entity.Categoria;
import com.upeu.categorias.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> obtenerTodas() {
        return categoriaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public Optional<CategoriaDTO> obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(this::convertirADTO);
    }

    public Optional<CategoriaDTO> obtenerPorNombre(String nombre) {
        return categoriaRepository.findByNombre(nombre)
                .map(this::convertirADTO);
    }

    public List<CategoriaDTO> obtenerActivas() {
        return categoriaRepository.findByEstado(true).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public CategoriaDTO crear(CategoriaDTO categoriaDTO) {
        Categoria categoria = convertirAEntidad(categoriaDTO);
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        return convertirADTO(categoriaGuardada);
    }

    public CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);
        if (categoriaExistente.isPresent()) {
            Categoria categoria = categoriaExistente.get();
            categoria.setNombre(categoriaDTO.getNombre());
            categoria.setDescripcion(categoriaDTO.getDescripcion());
            categoria.setEstado(categoriaDTO.getEstado());
            Categoria categoriaActualizada = categoriaRepository.save(categoria);
            return convertirADTO(categoriaActualizada);
        }
        return null;
    }

    public boolean eliminar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private CategoriaDTO convertirADTO(Categoria categoria) {
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getEstado(),
                categoria.getFechaCreacion(),
                categoria.getFechaActualizacion()
        );
    }

    private Categoria convertirAEntidad(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());
        categoria.setEstado(categoriaDTO.getEstado() != null ? categoriaDTO.getEstado() : true);
        return categoria;
    }
}
