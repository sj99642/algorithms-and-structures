package java_algorithms.misc;

public class TowersOfHanoi
{
    public static void main(String[] args)
    {
        TowersOfHanoi alg = new TowersOfHanoi();
        int disks;

        if (args.length == 1) {
            disks = Integer.parseInt(args[0]);
        } else {
            System.err.println("Must run as TowersOfHanoi disks");
            return;
        }

        alg.runAlgorithm(disks, "1", "2", "3");
    }

    public void runAlgorithm(int disks, String start, String middle, String dest)
    {
        if (disks == 1) {
            System.out.printf("%s -> %s\n", start, dest);
            return;
        }

        // Move the top disks to the middle pole
        runAlgorithm(disks - 1, start, dest, middle);

        // Move the bottom disk to the end
        System.out.printf("%s -> %s\n", start, dest);
        
        // Move the disks now on the middle to the destination
        runAlgorithm(disks - 1, middle, start, dest);
    }
}