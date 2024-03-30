//package me.thomaszoord.mysticmine.mine;
//
//public class oldMine {
//
//    private final PrisonPlayer owner;
//    private int size;
//    private HashMap<PrisonPlayer, HashMap<Location, Blocks>> mineLocations = new HashMap<>();
//    private HashMap<Location, Blocks> locationMaterialHashMap;
//
//
//    public oldMine(PrisonPlayer owner, int size) {
//        this.owner = owner;
//        this.size = size;
//
//        MysticMine.getMinesManager().addMine(owner, this);
//    }
//
//
//    public int mineSize(PrisonPlayer prisonPlayer){
//        //A EACH 10 TIERS UPGRADES THE MINE UPS ONE LEVEL
//        return 10 + (prisonPlayer.getTier() / 10);
//    }
//
//    public void createCube(Location startPosition){
//        Blocks[] materials = {Blocks.STONE, Blocks.IRON, Blocks.COAL};
//        Random random = new Random();
//
//        locationMaterialHashMap = new HashMap<>();
//        mineLocations.put(owner, locationMaterialHashMap);
//
//        int size = mineSize(owner);
//
//        for(int x = 0; x < size; x++)
//        {
//            for(int y = 0; y < size; y++)
//            {
//                for(int z = 0; z < size; z++){
//
//                    Blocks blockMaterial = materials[random.nextInt(materials.length)];
//
//                    sendPacket(owner.getPlayer(),
//                            startPosition.getBlockX() + x,
//                            startPosition.getBlockY() + y,
//                            startPosition.getBlockZ() + z, blockMaterial.getBlockType());
//
//                    Location location = new Location(startPosition.getWorld(), startPosition.getBlockX() + x, startPosition.getBlockY() + y, startPosition.getBlockZ() + z);
//                    locationMaterialHashMap.put(location, blockMaterial);
//
//                }
//
//            }
//        }
//    }
//    private void sendPacket(Player p, int x, int y, int z, Material blockMaterial)  {
//        PacketContainer payloadPacket = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);
//
//        payloadPacket.getBlockData().write(0, WrappedBlockData.createData(blockMaterial));
//        payloadPacket.getBlockPositionModifier().write(0, new BlockPosition(x, y, z));
//
//        MysticMine.getProtocolManager().sendServerPacket(p, payloadPacket);
//    }
//    public boolean isInsideMine(Block block){
//        return locationMaterialHashMap.containsKey(block.getLocation());
//    }
//
//
//    public PrisonPlayer getOwner() {
//        return owner;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public HashMap<PrisonPlayer, HashMap<Location, Blocks>> getMineLocations() {
//        return mineLocations;
//    }
//
//    public HashMap<Location, Blocks> getLocationMaterialHashMap() {
//        return locationMaterialHashMap;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//
//    public void setMineLocations(HashMap<PrisonPlayer, HashMap<Location, Blocks>> mineLocations) {
//        this.mineLocations = mineLocations;
//    }
//}
