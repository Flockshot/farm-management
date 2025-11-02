package me.somaan.farmapp.md5;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Handler extends Thread
{
    final private boolean isCheck;
    final private File animalFile;

    public MD5Handler(boolean isCheck, File animalFile)
    {
        super("MD5 Handler");
        this.isCheck = isCheck;
        this.animalFile = animalFile;

        start();
    }


    @Override
    public void run()
    {
        if(isCheck) {
            try {
                checkMD5(animalFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                createMD5(animalFile);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }






    private static void createMD5(File animalFile) throws NoSuchAlgorithmException, IOException
    {
        byte[] digest = getMD5(animalFile);

        File md5File = new File(animalFile.getParent(), "Animals.md5");

        if(!md5File.exists())
            md5File.createNewFile();

        FileOutputStream fileStream = new FileOutputStream(md5File);

        fileStream.write(digest);

        fileStream.close();
    }

    private static void checkMD5(File animalFile) throws IOException, NoSuchAlgorithmException
    {
        byte[] digest = getMD5(animalFile);
        File md5File = new File(animalFile.getParent(), "Animals.md5");
        if(md5File.exists())
        {
            FileInputStream in =  new FileInputStream(md5File);
            //ByteArrayInputStream bIn = new ByteArrayInputStream(in);
            byte[] savedDigest = in.readAllBytes();

            if(savedDigest.length == digest.length)
            {
                for(int i=0; i<savedDigest.length; i++)
                {
                    if(digest[i] != savedDigest[i])
                    {
                        System.out.println("MD5 File changed, Security Breach!!!");
                        in.close();
                        return;
                    }
                }

                System.out.println("MD5 is same!");
            }
            else
            {
                System.out.println("MD5 File changed, Security Breach!!!");
            }
            in.close();
        }
        else
            System.out.println("MD5 File for Animals does not exist, Security Breach!!!");

    }

    private static byte[] getMD5(File animalFile) throws IOException, NoSuchAlgorithmException
    {
        ByteArrayOutputStream byteSteam = new ByteArrayOutputStream();
        BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(animalFile));

        int ch;
        while ((ch = bufIn.read()) != -1)
            byteSteam.write (ch);

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();

        md5.update(byteSteam.toByteArray());

        byteSteam.close();
        bufIn.close();

        /*
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<digest.length;i++)
        {
            hexString.append (
                    Integer.toHexString(0xFF & digest[i]));
            hexString.append (" ");
        }
        System.out.println (hexString.toString());
        */

        return md5.digest();
    }

}
