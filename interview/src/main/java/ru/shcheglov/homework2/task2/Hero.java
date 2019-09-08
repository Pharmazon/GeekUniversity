package ru.shcheglov.homework2.task2;

import lombok.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Base64;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hero implements Externalizable {

    private String className;
    private String name;
    private int level;
    private int hp;
    private int maxHp;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(className);
        out.writeObject(name);
        out.writeObject(encryptString(String.valueOf(level)));
        out.writeObject(hp);
        out.writeObject(maxHp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.className = (String) in.readObject();
        this.name = (String) in.readObject();
        this.level = Integer.parseInt(decryptString(String.valueOf(in.readObject())));
        this.hp = (int) in.readObject();
        this.maxHp = (int) in.readObject();
    }

    private String encryptString(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    private String decryptString(String data) {
        return new String(Base64.getDecoder().decode(data));
    }
}
