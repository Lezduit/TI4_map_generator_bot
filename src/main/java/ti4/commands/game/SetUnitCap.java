package ti4.commands.game;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import ti4.commands.GameStateSubcommand;
import ti4.helpers.AliasHandler;
import ti4.helpers.Constants;
import ti4.map.Player;
import ti4.message.MessageHelper;

class SetUnitCap extends GameStateSubcommand {

    public SetUnitCap() {
        super(Constants.SET_UNIT_CAP, "Particular unit amount in reinforcements for a player.", true, true);
        addOptions(new OptionData(OptionType.STRING, Constants.FACTION_COLOR, "Color or faction that you're setting unit cap for").setAutoComplete(true).setRequired(true));
        addOptions(new OptionData(OptionType.STRING, Constants.UNIT_NAME, "Unit that you're setting the cap for").setRequired(true));
        addOptions(new OptionData(OptionType.INTEGER, Constants.UNIT_CAP, "Unit Cap").setRequired(true));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        int unitCap = event.getOption(Constants.UNIT_CAP).getAsInt();
        if (unitCap > 99) {
            unitCap = 99;
        }
        String unit = event.getOption(Constants.UNIT_NAME).getAsString();
        String unitID = AliasHandler.resolveUnit(unit);
        Player player = getPlayer();
        player.setUnitCap(unitID, unitCap);
        MessageHelper.sendMessageToChannel(event.getChannel(), "Set " + unit + " max to " + unitCap + " for " + player.getRepresentation());

    }
}
