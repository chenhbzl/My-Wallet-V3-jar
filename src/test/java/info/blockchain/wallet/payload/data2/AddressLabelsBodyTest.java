package info.blockchain.wallet.payload.data2;

import info.blockchain.wallet.payload.data.PayloadTest;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class AddressLabelsBodyTest {

    @Test
    public void fromJson_1() throws Exception {
        URI uri = PayloadTest.class.getClassLoader().getResource("wallet_body_1.txt").toURI();
        String body = new String(Files.readAllBytes(Paths.get(uri)), Charset.forName("utf-8"));

        WalletBody wallet = WalletBody.fromJson(body);
        HDWalletBody hdWallet = wallet.getHdWallet();

        List<AccountBody> accounts = hdWallet.getAccounts();

        List<AddressLabelsBody> addressLabels = accounts.get(1).getAddressLabels();
        Assert.assertEquals(98, addressLabels.get(0).getIndex());
        Assert.assertEquals("New Address", addressLabels.get(0).getLabel());

        Assert.assertEquals(23, accounts.get(2).getAddressLabels().get(0).getIndex());
        Assert.assertEquals("Contact request", accounts.get(2).getAddressLabels().get(0).getLabel());

        Assert.assertEquals(24, accounts.get(2).getAddressLabels().get(1).getIndex());
        Assert.assertEquals("Buy Sell", accounts.get(2).getAddressLabels().get(1).getLabel());
    }

    @Test
    public void testToJSON() throws Exception {

        //Ensure toJson doesn't write any unintended fields
        URI uri = PayloadTest.class.getClassLoader().getResource("wallet_body_1.txt").toURI();
        String body = new String(Files.readAllBytes(Paths.get(uri)), Charset.forName("utf-8"));

        WalletBody wallet = WalletBody.fromJson(body);
        HDWalletBody hdWallet = wallet.getHdWallet();

        List<AccountBody> accounts = hdWallet.getAccounts();

        List<AddressLabelsBody> addressLabels = accounts.get(1).getAddressLabels();
        String jsonString = addressLabels.get(0).toJson();

        JSONObject jsonObject = new JSONObject(jsonString);
        Assert.assertEquals(2, jsonObject.keySet().size());
    }
}