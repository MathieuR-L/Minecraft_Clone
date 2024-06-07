package test;

import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;
import org.jnbt.Tag;
import org.junit.Test;

import javax.imageio.stream.FileImageInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class TestNBTFile {

    public static void main(String[] args){
        String filePath = "res/schematics/building.nbt";

        try (FileInputStream fis = new FileInputStream(filePath)) {
            NBTInputStream nbtInputStream = new NBTInputStream(fis);

            Tag tag = nbtInputStream.readTag();

            if(tag instanceof CompoundTag) {
                CompoundTag compound = (CompoundTag) tag;
                System.out.println(compound);
            } else {
                System.out.println("Le fichier NBT ne contient pas un tag compound au niveau racine");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
