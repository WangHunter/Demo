package com.kafkaStorm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
import kafka.common.AuthorizationException;
import storm.kafka.*;

import java.util.ArrayList;
import java.util.List;

public class MykafkaSpout {

    /**
     * @param args
     * @throws AuthorizationException
     */
    public static void main(String[] args) throws AuthorizationException {
        // TODO Auto-generated method stub

        String topic = "storm" ;
        BrokerHosts zkHosts = new ZkHosts("ZJHZ-CMREAD-TEST208:2181,ZJHZ-CMREAD-TEST212:2181,ZJHZ-CMREAD-TEST213:2181");
        SpoutConfig spoutConfig = new SpoutConfig(zkHosts, topic,
                "",
                "MyTrack") ;
        List<String> zkServers = new ArrayList<String>();
        zkServers.add("ZJHZ-CMREAD-TEST212");
        spoutConfig.zkServers = zkServers;
        spoutConfig.zkPort = 2181;
        spoutConfig.socketTimeoutMs = 60 * 1000 ;
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        KafkaSpout kafkaSpout = new KafkaSpout(spoutConfig);

        TopologyBuilder builder = new TopologyBuilder() ;
        builder.setSpout("spout", kafkaSpout ,1) ;
        builder.setBolt("bolt", new MyKafkaBolt(), 2).shuffleGrouping("spout") ;

        Config conf = new Config ();
        conf.setDebug(false) ;

        if (args.length > 0) {
            try {
                StormSubmitter.submitTopology(args[0], conf, builder.createTopology());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("mytopology", conf, builder.createTopology());
        }

    }

}
