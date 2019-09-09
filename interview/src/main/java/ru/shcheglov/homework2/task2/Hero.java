package ru.shcheglov.homework2.task2;

import lombok.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode(exclude = "hashCode")
@NoArgsConstructor
@AllArgsConstructor
public class Hero implements Externalizable {

    private String className;
    private String name;
    private int level;
    private int hp;
    private int maxHp;
    private int hashCode = this.hashCode();

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(className);
        out.writeObject(name);
        out.writeObject(String.valueOf(level));
        out.writeObject(hp);
        out.writeObject(maxHp);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.className = (String) in.readObject();
        this.name = (String) in.readObject();
        this.level = Integer.parseInt(String.valueOf(in.readObject()));
        this.hp = (int) in.readObject();
        this.maxHp = (int) in.readObject();
    }

}
