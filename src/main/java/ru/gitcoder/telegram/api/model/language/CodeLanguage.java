package ru.gitcoder.telegram.api.model.language;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public enum CodeLanguage {
    PYTHON("python"),
    JAVA("java"),
    JAVASCRIPT("javascript"),
    TYPESCRIPT("typescript"),
    CPP("cpp"),
    C("c"),
    CSHARP("csharp"),
    PHP("php"),
    RUBY("ruby"),
    GO("go"),
    SWIFT("swift"),
    KOTLIN("kotlin"),
    SCALA("scala"),
    RUST("rust"),
    PERL("perl"),
    R("r"),
    MATLAB("matlab"),
    SHELL("shell"),
    BASH("bash"),
    POWERSHELL("powershell"),
    SQL("sql"),
    HTML("html"),
    CSS("css"),
    XML("xml"),
    JSON("json"),
    YAML("yaml"),
    MARKDOWN("markdown"),
    DOCKERFILE("dockerfile"),
    GROOVY("groovy"),
    LUA("lua"),
    HASKELL("haskell"),
    ELIXIR("elixir"),
    DART("dart"),
    OBJECTIVEC("objectivec"),
    VB("vb"),
    COFFEESCRIPT("coffeescript"),
    FSHARP("fsharp"),
    ERLANG("erlang"),
    CLOJURE("clojure"),
    SCHEME("scheme"),
    LISP("lisp"),
    FORTRAN("fortran"),
    COBOL("cobol"),
    PASCAL("pascal"),
    ADA("ada"),
    PROLOG("prolog"),
    D("d"),
    JULIA("julia"),
    OCAML("ocaml"),
    UNKNOWN(""); // Для неизвестных языков, пустой строки или null

    private final String name;

    private static final Map<String, CodeLanguage> BY_NAME = new HashMap<>();

    static {
        for (CodeLanguage lang : values()) {
            BY_NAME.put(lang.name, lang);
        }
        BY_NAME.put("", UNKNOWN);
    }

    @JsonCreator
    public static CodeLanguage fromName(String name) {
        return BY_NAME.getOrDefault(name != null ? name.toLowerCase() : "", UNKNOWN);
    }
}