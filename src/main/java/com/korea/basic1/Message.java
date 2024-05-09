package com.korea.basic1;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private String message;
    private String href;

    public Message(String message,
                   String href) {

        this.message = message;
        this.href = href;
    }
}