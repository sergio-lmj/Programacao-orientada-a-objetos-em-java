package br.com.carros.controller;

import br.com.carros.model.Carro;
import br.com.carros.repository.CarroRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping("/gerenciarCarros")
    public String listarCarros(Model model) {
        model.addAttribute("listaCarros", carroRepository.findAll());
        return "gerenciar_carros";
    }

    @GetMapping("/novoCarro")
    public String novoCarro(Model model) {
        model.addAttribute("carro", new Carro());
        return "editar_carro";
    }

    @GetMapping("/editarCarro/{id}")
    public String editarCarro(@PathVariable("id") long idCarro, Model model) {
        Optional<Carro> carro = carroRepository.findById(idCarro);
        model.addAttribute("carro", carro.get());
        return "editar_carro";
    }

    @PostMapping("/salvarCarro")
    public String salvarCarro(Carro carro, BindingResult result) {
        if (result.hasErrors()) {
            return "editar_carro";
        }
        carroRepository.save(carro);
        return "redirect:/gerenciarCarros";
    }

    @GetMapping("/excluirCarro/{id}")
    public String excluirCarro(@PathVariable("id") long idCarro) {
        carroRepository.deleteById(idCarro);
        return "redirect:/gerenciarCarros";
    }
}

