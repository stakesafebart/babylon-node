package utils

import com.google.common.collect.ImmutableSet
import com.radixdlt.test.SystemCounters
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class TestnetNodes {

    private static final TestnetNodes instance = new TestnetNodes();
    private static final Logger logger = LogManager.getLogger(TestnetNodes.class);
    private static final String scheme = "https"
    private TestnetNodes() {
    }


    private List<String> nodes
    String keyVolume = "key-volume"
    private String sshDestinationLocDir = "/ansible/ssh"
    private String sshDestinationFileName = "testnet"
    String ansibleImage = "eu.gcr.io/lunar-arc-236318/node-ansible:python3"

    static TestnetNodes getInstance() {
        return instance
    }

    String getNodesURls() {
        fetchNodes()
        return nodes.inject("", {
            str, node ->
                if (this.nodes.first() == node)
                    return "${str}https://${node}"
                return "${str},https://${node}"
        })
    }

    ImmutableSet<String> nodeURLList() {
        fetchNodes()

        return ImmutableSet.copyOf(this.nodes.collect {
            "${scheme}://${it}" as String
        });
    }

    private fetchNodes() {
        if (nodes == null) {
            String clusterName = Optional.ofNullable(System.getenv("TESTNET_NAME")).orElse("testnet_2")
            logger.info("Node information not avaliable. Fetching using ansible")
            String sshKeylocation = Optional.ofNullable(System.getenv("SSH_IDENTITY")).orElse(System.getenv("HOME") + "/.ssh/id_rsa")
            CmdHelper.runCommand("docker container create --name dummy -v ${keyVolume}:${sshDestinationLocDir} curlimages/curl:7.70.0")
            CmdHelper.runCommand("docker cp ${sshKeylocation} dummy:${sshDestinationLocDir}/${sshDestinationFileName}")
            CmdHelper.runCommand("docker rm -f dummy")
            def output, error
            (output, error) = CmdHelper.runCommand("docker run --rm  -v ${keyVolume}:/ansible/ssh --name node-ansible ${ansibleImage}  "
                    + "check.yml --limit ${clusterName} --list-hosts")
            nodes = output.findAll({
                !(it.contains("play") || it.contains("pattern") || it.contains("hosts") || it == "")
            }).collect({ it.trim() })

        } else {
            logger.info("Nodes information already present ${nodes}")
        }
    }
}
