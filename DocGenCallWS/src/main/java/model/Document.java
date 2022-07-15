package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashMap;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Document {

    private LinkedHashMap <Integer, String> documentMap;
}
