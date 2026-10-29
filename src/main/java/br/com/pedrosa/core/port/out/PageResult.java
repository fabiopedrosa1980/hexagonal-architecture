package br.com.pedrosa.core.port.out;

import java.util.List;

public record PageResult<T>(List<T> content, long totalElements) {}
